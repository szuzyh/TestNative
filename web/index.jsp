<%--
  Created by IntelliJ IDEA.
  User: Leo
  Date: 2016/12/8
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style type="text/css">
      #cam{
        position: absolute;
        top: 10%;
        left: 20%;
        margin-left: -200px;
        margin-top: -50px;
      }
    </style>
    <script type="text/javascript">



    </script>
    <title>身份证登记</title>
  </head>
  <body>
  <div id="cam"></div>

  <form action="CreateVideoServlet" method="GET">
      <input type="hidden" name="c" id="cv1">
    <input   type="submit" value="录像" >
  </form>
  <script type="text/javascript" src="../webcam.js"></script>
  <script language="JavaScript">
    Webcam.set({
        index:1,
      width: 640,
      height: 480,
      force_flash: true,
      image_format: 'jpeg',
      jpeg_quality: 90
    });
    Webcam.attach( '#cam' );
  </script>
  </body>
</html>
