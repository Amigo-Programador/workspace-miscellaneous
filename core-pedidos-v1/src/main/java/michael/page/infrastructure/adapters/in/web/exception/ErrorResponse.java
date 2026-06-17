package michael.page.infrastructure.adapters.in.web.exception;

import java.util.List;

public record ErrorResponse(String code,
                            String message,
                            List<String> details,
                            String correlationId) {
}
