<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
%>
<%@ page import="java.util.Map,
	java.net.URLDecoder,db.Storage,pojo.Banner"
%>

<% 	
String url = request.getParameter("url");
Map<String, Banner> bannerMap = Storage.getBannerMap();
Banner banner = bannerMap.get(url);

if(banner == null) {
	return;
}

System.out.println("Banner obj: " + banner.toString());

String title = banner.getTitle();
String description = banner.getDescription();
%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Site</title>
		<link href="../css/bootstrap.min.css" rel="stylesheet"/>
		<link href="../css/bannerSitePage.css" rel="stylesheet">
		<script src="../jquery-3.6.0.min.js"></script>
		<script src="./bannerSitePage.js"></script>
	</head>
	<body>
		<div class="container">
			<div class="banner">
				<span><b>Title: </b><%= title %></span>
				<span><b>Description: </b><%= description %></span>
				<button id="banner-button" class="btn-danger">Subscribe</button>
			</div>
			<br/>
			<iframe id="site-window" src="<%= url %>"></iframe>
		</div>
	</body>
</html>