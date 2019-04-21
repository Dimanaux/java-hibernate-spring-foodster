document.getElementById("add__button").onclick = function addButton() {
	//Calculate amount of existing ingredients
    let form = document.getElementById("form");
    let amount = form.getElementsByTagName("div").length;
	amount++;

	let newDiv = document.createElement("div");
	newDiv.classList.add("ingredient");
	form.insertBefore(newDiv, form.children[amount - 1]);

	let newSelect = document.createElement("select");
	newSelect.name = "ingredient-select " + amount;
	newSelect.id = "ingredient-select " + amount;
	newDiv.appendChild(newSelect);

	$.ajax({
		url: "url",
		type: "get",
		dataType: "json",
		success: function(data) {
			$.each(data, function(i, item) {
				let option = document.createElement("option");
				option.value = item.value;
				option.text = item.value;
				newSelect.appendChild(option);
			});
		}
	});

};