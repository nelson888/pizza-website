export const PAGE_SIZE = 2;
//TODO TO DELETE
const ingredients = [{
    name:"poivre",
    ingredient_id:0
}, {
    name: "sel",
    ingredient_id:1
}, {name:"tomate",
    ingredient_id:2
}];

const allPizzas = [
    {id: 0, title: "pizza 1", ingredients: [ingredients[0], ingredients[1]]},
    {id: 1, title: "Contende", ingredients: [ingredients[0], ingredients[2]]},
    {id: 2, title: "Castalone", ingredients: [ingredients[1], ingredients[2]]},
    {id: 3, title: "Pierone", ingredients: ingredients},
    {id: 4, title: "Cierone", ingredients: ingredients},
];

export function getPizzas(page, size) {
    page = page || 0;
    size = size || PAGE_SIZE;

    let sliced = [];
    const startIndex = page * size;
    for (let i = startIndex; i < startIndex + size && i < allPizzas.length; i++) {
        sliced.push(allPizzas[i])
    }
    return sliced;
}

export function getPizzasSearch(page, searchQuery) {
    page = page || 0;
    const size = PAGE_SIZE;

    let sliced = [];
    const startIndex = page * size;
    const filtered = filteredPizzas(searchQuery);

    for (let i = startIndex; i < startIndex + size && i < filtered.length; i++) {
        sliced.push(filtered[i])
    }
    return sliced;
}

function filteredPizzas(searchQuery) {
    return allPizzas.filter(p => p.title.toLowerCase().startsWith(searchQuery.toLowerCase()));
}
export function getPizzaCount(searchQuery) {
    if (searchQuery) {
        const filtered = filteredPizzas(searchQuery);
        return filtered.length;
    }
    return allPizzas.length;
}

export function getAllIngredients() {
    return ingredients;
}

export function getPizzaDoughs() {
    return [{id:0, name:'thin'},
        {id:1, name:'normal'},
        {id:2, name:'thick'}]
}

export function savePizza(pizza) {
    //TODO handle ingredients
    pizza.id = allPizzas.length;
    allPizzas.push(pizza);
}