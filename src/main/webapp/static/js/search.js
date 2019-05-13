const createRecipe = (list, recipe) => {
    list.append(
        `<a href="/recipes/${recipe.id}">
            <div class="item">
                <h4>${recipe.title}</h4>
                <ul class="ingredients">
                    <li>${recipe.ingredients.map(i => i.name).join('</li><li>')}</li>
                </ul>
            </div>
        </a>`
    );
};

const search = () => {
    let query = $('#search-box').val();
    if (query.length < 4) {
        return;
    }
    $.ajax({
        url: '/search.json',
        type: 'GET',
        data: {query: query},
        success: (data) => {
            let list = $('#recipes-list');
            list.empty();
            for (let recipe of data) {
                createRecipe(list, recipe);
            }
        }
    });
};
