<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="css/album.css" rel="stylesheet">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Lato", sans-serif}
.w3-bar,h1,button {font-family: "Montserrat", sans-serif}
.fa-anchor,.fa-coffee {font-size:200px}
</style>
</head>
<body>

<div class="w3-top">
  <div class="w3-bar w3-red w3-card w3-left-align w3-large">
    <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-red" href="javascript:void(0);" onclick="myFunction()" title="Toggle Navigation Menu"><i class="fa fa-bars"></i></a>
    <a href="/MovieRecommender_24Frames/template.jsp" class="w3-bar-item w3-button w3-padding-large w3-white">Home</a>
    <a href="/MovieRecommender_24Frames/template.jsp" class="w3-bar-item w3-button w3-padding-large w3-white">Link 1</a>
    <a href="/MovieRecommender_24Frames/genreDirectorActorTag.jsp" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Link 2</a>
<a href="/MovieRecommender_24Frames/popularDirectorActor.jsp" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Link 3</a>
    <a href="#" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white">Link 4</a>
  </div>

  <!-- Navbar on small screens -->
  <div id="navDemo" class="w3-bar-block w3-white w3-hide w3-hide-large w3-hide-medium w3-large">
    <a href="#" class="w3-bar-item w3-button w3-padding-large">Link 1</a>
    <a href="#" class="w3-bar-item w3-button w3-padding-large">Link 2</a>
    <a href="#" class="w3-bar-item w3-button w3-padding-large">Link 3</a>
    <a href="#" class="w3-bar-item w3-button w3-padding-large">Link 4</a>
  </div>
</div>

<!-- Header -->
<header class="w3-container  w3-center" style="padding:20px 16px">
  <h1 class="w3-margin w3-jumbo">24 Frames</h1>
  <h3 >Choose an Actor/Director/Genre to view their top movies by audience score</h3>
     <div id="moviesByScore" style="width:700px;height:500px;overflow-x:scroll">
   <s:form action="genreDirectorActorTag">
   <table>
    	<tr>
    	<s:select label="Select Actor/Director/Genre:"
	headerKey="-1" headerValue="select"
	list="#{'actor':'Actor', 'director':'Director', 'genre':'Genre','tag':'Tags' }"
	name="selectedValue" />
		</tr>
		<tr>
		<td><s:textfield label="Enter Name" name="name"/>
    		
    	</td>
		</tr>
		<tr>
		<td><s:textfield label="Enter the top number of movies you want to search" name="userInputk"/>
    		<s:submit value="Submit"/> 
    	</td>
		</tr>
	<tr><td></td></tr>
   </table>

   <table border=1 style="width:550px;height:600px">
<s:iterator value="topkMoviesInSelected" status="movie">
<tr>
  <td style="width:100px"><s:property value="title"/></td>
  <td style="width:60px"><s:property value="year"/></td>
  <td style="width:60px"><s:property value="rtAudienceScore"/></td>  
  <td style="width:130px;height:170px;background-image:url(<s:property value="rtPictureURL"/>)"></td>
  <td style="width:130px;height:170px;background-image:url(<s:property value="imdbPictureURL"/>)"></td>
</tr>
</s:iterator>
</table>
</s:form>
</div>
  <button class="w3-button w3-black w3-padding-large w3-large w3-margin-top">Get Started</button>
</header>

<!-- First Grid -->
<div class="w3-row-padding w3-padding-64 w3-container">
  <div class="w3-content">
    <div class="w3-twothird">
      
    </div>

    <div class="w3-third w3-center">
      
    </div>
  </div>
</div>

<!-- Second Grid -->
<div class="w3-row-padding w3-light-grey w3-padding-64 w3-container">
  <div class="w3-content">
    <div class="w3-third w3-center">
   
    </div>

    <div class="w3-twothird">
      
    </div>
  </div>
</div>

<div class="w3-container w3-black w3-center w3-opacity w3-padding-64">
    <h1 class="w3-margin w3-xlarge">Quote of the day: live life</h1>
</div>

<script>
// Used to toggle the menu on small screens when clicking on the menu button
function myFunction() {
    var x = document.getElementById("navDemo");
    if (x.className.indexOf("w3-show") == -1) {
        x.className += " w3-show";
    } else { 
        x.className = x.className.replace(" w3-show", "");
    }
}
</script>

</body>
</html>
