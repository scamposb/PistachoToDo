function getTasks() {
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
	    if (xmlhttp.readyState == 4) {
	    	var x = xmlhttp.response.indexOf("<table class=\"table\" id=\"toDoTable\"");
	    	x = xmlhttp.response.indexOf(">", x-4);
	    	var y = xmlhttp.response.indexOf("</table>", x);
	    	var tableContent = xmlhttp.response.slice(x+1,y);
	    	
	    	if(tableContent !== ""){
	    		document.getElementById("toDoTable").innerHTML = tableContent;
	    	}
	    }
	};
	xmlhttp.open("GET","/loadServlet",true);
	xmlhttp.send(null);
}