/*
 * Javascript that shows new row to add
 */

/**
 * Function that swaps display between "add row" button
 * and new row form 
 */
function addNewRow(){
	$(document.getElementById('addNewRowButton')).fadeOut(function(){
		$(document.getElementById('addNewRowForm')).fadeIn();
	});
	//document.getElementById('addNewRowButton').style.display = 'none';
	//document.getElementById('addNewRowForm').style.display = 'inline';
};