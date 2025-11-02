import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoadCities {

  /*
    |------------------------------------------------------|
    |                   Road & Cities                      |
    |------------------------------------------------------|
    | 0 = Acuario | 1 = Boulevard | 2 = Coliseo | 3 = Dojo |
    |------------------------------------------------------|
    |          | 0 | 1 | 2 | 3 | 4 |                       |
    | Cities =   1   0   3   2   2                         |
    | Roads = [0, 1], [1, 2], [1, 4], [2, 3], [2, 4]       |
    |------------------------------------------------------|
  */
  public static void main(String[] args) {
//    List<String> citiesString = Arrays.asList("B", "A", "D", "C", "C");
    List<Integer> cities = Arrays.asList(1, 0, 3, 2, 2);

    List<int[]> roads = new ArrayList<>();
    roads.add(new int[]{0, 1});
    roads.add(new int[]{1, 2});
    roads.add(new int[]{1, 4});
    roads.add(new int[]{2, 3});
    roads.add(new int[]{2, 4});

//    int result = countValidRoutesString(citiesString, roads);
    int result = countValidRoutes(cities, roads);
    System.out.println("Number of valid paths: " + result);

  }

  public static int countValidRoutes(List<Integer> cities, List<int[]> roads) {
    int[] citiesVisited = new int[4];
    int validRoutes = 0;
    System.out.println("[init] countValidRoutes");
    System.out.println("[cities] " + cities + " | [B, A, D, C, C]");
    for (int i = 0; i < cities.size(); i++) {
      Arrays.fill(citiesVisited, 0);
      citiesVisited[cities.get(i)]++;
      System.out.println("[for-city] i="+i + "   type: " + getTypeCity(cities.get(i))+ "   citiesVisited: "+ Arrays.toString(citiesVisited));
      validRoutes += findRoute(cities, roads, citiesVisited, i, 2);
      System.out.println("validRoutes: "+ validRoutes);
    }

    return validRoutes;
  }

//  public static int countValidRoutesString(List<String> cities, List<int[]> roads) {
//    int[] citiesVisited = new int[4];
//    int validRoutes = 0;
//    System.out.println("[init] countValidRoutes");
//    for (int i = 0; i < cities.size(); i++) {
//      System.out.println("  [for] i="+i);
//      Arrays.fill(citiesVisited, 0);
//
//
//
//      citiesVisited[getIntegerIndex(cities.get(i))]++;
//      System.out.println("  citiesVisited: "+ Arrays.toString(citiesVisited));
//      validRoutes += findRouteString(cities, roads, citiesVisited, i);
//      System.out.println("  validRoutes: "+ validRoutes);
//    }
//
//    return validRoutes;
//  }

  private static int findRoute(List<Integer> cities, List<int[]> roads, int[] citiesVisited, int currentCity, int depth) {
    System.out.println(indent(depth) + "[bucle] currentCity: "+currentCity + "   type: "+ getTypeCity(cities.get(currentCity)) );
    if (allCitiesVisited(citiesVisited)) {
      System.out.println(indent(depth) + "*** VALID ROUTE ***");
      return 1;
    }

    int validRoutes = 0;
    for (int i = 0; i < roads.size(); i++) {
      System.out.println(indent(depth) + "[for-road] i="+i + "   road: "+ Arrays.toString(roads.get(i))+ "   citiesVisited: "+ Arrays.toString(citiesVisited));
      int[] road = roads.get(i);
      if (road[0] == currentCity && citiesVisited[cities.get(road[1])] == 0) {
        System.out.println(indent(depth)+"  [if] city: "+road[0] + " type: " + getTypeCity(cities.get(road[0])) +" → city: "+road[1] + " type: " + getTypeCity(cities.get(road[1])));

        citiesVisited[cities.get(road[1])]++;
        System.out.println(indent(depth)+"  update++  citiesVisited:" + Arrays.toString(citiesVisited));

        validRoutes += findRoute(cities, roads, citiesVisited, road[1], depth+4);

        citiesVisited[cities.get(road[1])]--;
        System.out.println(indent(depth)+"  update--  citiesVisited:" + Arrays.toString(citiesVisited));
      } else if (road[1] == currentCity && citiesVisited[cities.get(road[0])] == 0) {
        System.out.println(indent(depth)+"  [if] city: "+road[1] + " type: " + getTypeCity(cities.get(road[1])) +" → city: "+road[0] + " type: " + getTypeCity(cities.get(road[0])));
        citiesVisited[cities.get(road[0])]++;
        System.out.println(indent(depth)+"  update++  citiesVisited:" + Arrays.toString(citiesVisited));
        validRoutes += findRoute(cities, roads, citiesVisited, road[0], depth+4);
        citiesVisited[cities.get(road[0])]--;
        System.out.println(indent(depth)+"  update--  citiesVisited:" + Arrays.toString(citiesVisited));
      }
    }
    return validRoutes;
  }

//  private static int findRouteString(List<String> cities, List<int[]> roads, int[] citiesVisited, int currentCity) {
//    System.out.println("  [bucle] findRoute currentCity="+currentCity);
//    if (allCitiesVisited(citiesVisited)) {
//      return 1;
//    }
//
//    int validRoutes = 0;
//    for (int i = 0; i < roads.size(); i++) {
//      System.out.println("    [for] i="+i + " road="+ Arrays.toString(roads.get(i)));
//      int[] road = roads.get(i);
//      if (road[0] == currentCity && citiesVisited[getIntegerIndex(cities.get(road[1]))] == 0) {
//        System.out.println("    [if] road[0]="+road[0] + "  typeCity="+cities.get(road[1]));
//        citiesVisited[getIntegerIndex(cities.get(road[1]))]++;
//        validRoutes += findRouteString(cities, roads, citiesVisited, road[1]);
//        citiesVisited[getIntegerIndex(cities.get(road[1]))]--;
//      } else if (road[1] == currentCity && citiesVisited[getIntegerIndex(cities.get(road[0]))] == 0) {
//        System.out.println("    [else] road[1]="+road[1] + "  typeCity="+cities.get(road[1]));
//        citiesVisited[getIntegerIndex(cities.get(road[0]))]++;
//        validRoutes += findRouteString(cities, roads, citiesVisited, road[0]);
//        citiesVisited[getIntegerIndex(cities.get(road[0]))]--;
//      }
//    }
//    return validRoutes;
//  }

  private static boolean allCitiesVisited(int[] citiesVisited) {
    for (int count : citiesVisited) {
      if (count == 0) {
        return false;
      }
    }
    return true;
  }

//  private static int getIntegerIndex(String i) {
//    switch (i) {
//      case "A": return 0;
//      case "B": return 1;
//      case "C": return 2;
//      case "D": return 3;
//      default: throw new IllegalArgumentException("Not valid index");
//    }
//  }

  private static String getTypeCity(int i) {
    switch (i) {
      case 0: return "A";
      case 1: return "B";
      case 2: return "C";
      case 3: return "D";
      default: throw new IllegalArgumentException("Not valid index: " + i);
    }
  }

  private static String indent(int depth) {
    return " ".repeat(depth);
  }
}
