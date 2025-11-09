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

  private static List<Integer> cities = Arrays.asList(1, 0, 3, 2, 2);
  private static List<int[]> roads = new ArrayList<>();

  private static int contador = 1;
  private static List<List<Integer>> validRoutes = new ArrayList<>();
  private static int[] typeVisited = new int[4]; // 0,0,0,0

  public static void main(String[] args) {

    roads.add(new int[]{0, 1});
    roads.add(new int[]{1, 2});
    roads.add(new int[]{1, 4});
    roads.add(new int[]{2, 3});
    roads.add(new int[]{2, 4});

    findValidRoutes(cities, roads);

    System.out.println("Valid routes: " + validRoutes);

  }

  private static List<List<Integer>> findValidRoutes(List<Integer> cities, List<int[]> roads) {
    System.out.println("[init] findValidRoutes");



    for(int c=0; c<cities.size(); c++) {
      System.out.println("[for-city="+c+"] type=" + getTypeCity(cities.get(c)));

      Arrays.fill(typeVisited, 0);
      typeVisited[cities.get(c)]++; // 0,1,0,0
      List<Integer> validRoute = new ArrayList<>();
      validRoute.add(c);
      recursiveFindRoute(c, validRoute, 0);
      System.out.println("[for-city="+c+"]");
    }

    System.out.println("[end] findValidRoutes");
    return validRoutes;
  }


  private static List<List<Integer>> recursiveFindRoute(Integer currentCity, List<Integer> validRoute, int depth) {
    int nro = contador++;
    System.out.println(indent(depth+2)+"[recursive-"+nro+"] city="+currentCity + " type="+ getTypeCity(cities.get(currentCity)) );

    if(validVisitAllTypes(typeVisited)) {
      System.out.println(indent(depth+4)+"*** ALL VISITED ***");
      validRoutes.add(new ArrayList<>(validRoute));
      System.out.println(indent(depth+4)+validRoutes);
      System.out.println(indent(depth+2)+"[recursive-"+nro+"]");
      return validRoutes;
    }


    for(int r=0; r<roads.size(); r++) {
      System.out.println(indent(depth+4)+"[for-road-" + nro + "] " + "road="+r+" "+ Arrays.toString(roads.get(r))+ " [" + getTypeCity(cities.get(roads.get(r)[0])) +", " + getTypeCity(cities.get(roads.get(r)[1]))
        + "] citiesVisited: "+ Arrays.toString(typeVisited) + "  validRoute: " + validRoute);

      int[] road = roads.get(r);
      if(currentCity == road[0] && typeVisited[cities.get(road[1])] == 0) {
        System.out.println(indent(depth+6)+"[if-"+nro+"] " + getTypeCity(cities.get(road[0])) + " → " + getTypeCity(cities.get(road[1])) + " = " + getTypeCity(cities.get(road[1])) + " Not visited");
        typeVisited[cities.get(road[1])]++;
        validRoute.add(road[1]);

        recursiveFindRoute(road[1], validRoute, depth+6);

        typeVisited[cities.get(road[1])]--;
        validRoute.remove(validRoute.size()-1);

        System.out.println(indent(depth+6)+"typeVisited--: "+ Arrays.toString(typeVisited));
        System.out.println(indent(depth+6)+"validRoute.remove() "+ validRoute);
        System.out.println(indent(depth+6)+"[if-"+nro+"] ");
      }
      if(currentCity == road[1] && typeVisited[cities.get(road[0])] == 0) {
        System.out.println(indent(depth+6)+"[else-"+nro+"] " + getTypeCity(cities.get(road[0])) + " ← " + getTypeCity(cities.get(road[1])) + " = " + getTypeCity(cities.get(road[0])) + " Not visited");
        typeVisited[cities.get(road[0])]++;
        validRoute.add(road[0]);

        recursiveFindRoute(road[0], validRoute, depth+6);

        typeVisited[cities.get(road[0])]--;
        validRoute.remove(validRoute.size()-1);

        System.out.println(indent(depth+6)+"typeVisited--: "+ Arrays.toString(typeVisited));
        System.out.println(indent(depth+6)+"validRoute.remove() "+ validRoute);
        System.out.println(indent(depth+6)+"[else-"+nro+"]");
      }

    }
    System.out.println(indent(depth+2)+"[recursive-"+nro+"]");
    return validRoutes;
  }


  private static boolean validVisitAllTypes(int[] typeVisited) {
    for(int i : typeVisited) {
      if(i==0) {
        return false;
      }
    }
    return true;
  }


  private static String getTypeCity(int type) {
    switch (type) {
      case 0: return "A";
      case 1: return "B";
      case 2: return "C";
      case 3: return "D";
      default: throw new IllegalArgumentException("Not valid type city");
    }
  }

  private static String indent(int depth) {
    return " ".repeat(depth);
  }

}
