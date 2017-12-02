<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
  <%@ taglib prefix="s" uri="/struts-tags"%>

<!doctype html>
<html lang="en">
  <head><title>Movie Recommender</title></head>
  <body>
  
    <div id="moviesByScore" style="width:500px;height:500px;overflow-x:scroll">
   <s:form action="movieByScore">
   <table>
    	<tr>
    	<td><s:textfield label="movies number" name="userInputk"/>
    		<s:submit value="Submit" name="go"/> 
    	</td>
		</tr>
   </table>

   <table border=1 style="width:550px;height:600px">
<s:iterator value="topkMoviesListbyScore" status="movie">
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

<div id="moviesNames" style="width:500px;height:500px;overflow-x:scroll">
   <s:form action="movieByName">
   <table>
    	<tr>
    	<td><s:textfield label="Movie Name" name="userInputMovie"/>
    		<s:submit value="Submit" name="go"/> 
    	</td>
		</tr>
   </table>

<table border=1 style="width:550px;height:600px">
<s:iterator value="moviesByName" status="movie">
<tr>
  <td style="width:100px"><s:property value="title"/></td>
  <td style="width:60px"><s:property value="year"/></td>
  <td style="width:60px"><s:property value="rtAudienceScore"/></td>  
  <td style="width:130px;height:170px;background-image:url(<s:property value="rtPictureURL"/>)"></td>
  <td style="width:130px;height:170px;background-image:url(<s:property value="imdbPictureURL"/>)"></td>
  <td style="width:60px"><s:property value="userTags"/></td>
</tr>
</s:iterator>
</table>
</s:form>
</div>

  </body>
  </html>