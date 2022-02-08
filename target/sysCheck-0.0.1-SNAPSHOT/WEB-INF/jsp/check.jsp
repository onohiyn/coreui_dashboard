<%@ page language="java" import= "java.io.*, java.util.*, java.net.* "
 contentType="text/html;charset=UTF-8" session="false" %>
<!DOCTYPE html>
<html>
<head>
<style>
a:link { color:gray; text-decoration: none;}
#a:visited { color:white; text-decoration: none;}
a:visited { color:#1C1C1C; text-decoration: none;}
a:hover{ color:#7B68EE; text-decoration: underline; font-size:15pt;}
a:active{ color:#7B68EE;}

thead th {
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #369;
    border-bottom: 3px solid #036;
}
tbody th {
    width: 500px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #f3f6f7;
}
td {
    width: 500px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
}
table { border-collapse: collapse;
    text-align: left;
    line-height: 1.5; }

</style>

<meta charset="UTF-8">
<title>Check Process</title>
</head>
<BODY style="FONT-SIZE: 12.5pt">

<table class="js-sort-table" id="demo">
    <thead>
	    <tr>
	        <th scope="cols">System Check 시스템 체크</th>
	    </tr>
	</thead>
    <tbody>
		  <tr>
		      <th scope="row">
		      <form action="check" method="post">
		      <target="_blank"> <select name="system" style="width:300px;height:20px;">
		      <option value="dev"  ${tmp == 'dev' ? 'selected="selected"' : '' }>Dev (개발)</option>
              <option value="prod" ${tmp == 'prod' ? 'selected="selected"' : '' }>Prod (운영)</option>
              </select></a>
              <button type="submit"> submit </button>
              </form>
              </th>
		  </tr>
		  <tr> <td>${result}</td> </tr>
    </tbody>
</table>

</body>
</html>
