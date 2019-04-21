document.getElementById("add__button").onclick = function addButton() {
	//Calculate amount of existing ingredients
	var form = document.getElementById("form");
	var amount = form.getElementsByClassName("ingredient").length;
	amount++;

	var newDiv = document.createElement("div");
	newDiv.classList.add("ingredient");
	form.insertBefore(newDiv, form.children[amount - 1]);

	var newInnerDiv = document.createElement("div");
	newInnerDiv.classList.add("ingredient-selection");
	newDiv.appendChild(newInnerDiv);

	var input = document.createElement("input");
	input.classList.add("amount");
	input.name = "ingredient-amount " + amount; 
	input.id = "ingredient-amount " + amount;
	input.placeholder = "Amount";
	newDiv.appendChild(input);

	var newSelect = document.createElement("select");
	newSelect.name = "ingredient-select " + amount;
	newSelect.id = "ingredient-select " + amount;
	newInnerDiv.appendChild(newSelect);

	$.ajax({
		url: "url",
		type: "get",
		dataType: "json",
		success: function(data) {
			$.each(data, function(i, item) {
				var option = document.createElement("option");
				option.value = item.value;
				option.text = item.value;
				newSelect.appendChild(option);
			});
		}
	});
};
