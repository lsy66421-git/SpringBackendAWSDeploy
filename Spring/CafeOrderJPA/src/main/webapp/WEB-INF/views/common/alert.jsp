<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>알림</title>
        </head>

        <body>
            <script type="text/javascript">
                var msg = "${msg}";
                var url = "${url}";

                if (msg) {
                    alert(msg);
                }

                if (url == 'back') {
                    history.back();
                } else {
                    location.href = "${pageContext.request.contextPath}" + url;
                }
            </script>
        </body>

        </html>