document.getElementById("add__button").onclick = function addButton() {
	//Calculate amount of existing ingredients
	var form = document.getElementById("form");
	var amount = form.getElementsByTagName("div").length;
	amount++;

	var newDiv = document.createElement("div");
	newDiv.classList.add("ingredient");
	form.insertBefore(newDiv, form.children[amount - 1]);

	var newSelect = document.createElement("select");
	newSelect.name = "ingredient-select " + amount;
	newSelect.id = "ingredient-select " + amount;
	newDiv.appendChild(newSelect);

	var array = ["ingr1", "ingr2", "ingr3", "ingr4"]

	for (var i = 0; i < array.length; i++) {
		var option = document.createElement("option");
		option.value = array[i];
		option.text = array[i];
		newSelect.appendChild(option);
	}
}