const createRecipe = (recipe) => {
    let list = $('#recipes-list');
    list.append(
        `<a href="/recipes/${recipe.id}">
            <div class="item">
                <h4>${recipe.title}</h4>
                <small>${recipe.date}</small>
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
            $('#recipe-item').remove();
            for (let recipe of data) {
                createRecipe(recipe);
            }
        }
    });
};
