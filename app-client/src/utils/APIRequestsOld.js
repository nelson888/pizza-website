export const API_BASE_URL = 'http://localhost:8080/api';
//export const API_BASE_URL = '/api';
export const ACCESS_TOKEN = 'accessToken';

export const PAGE_SIZE = 2;

const request = (options) => {
    const headers = new Headers({
        'Content-Type': 'application/json',
    });

    if(localStorage.getItem(ACCESS_TOKEN)) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
    }

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    return fetch(options.url, options)
        .then(response =>
            response.json().then(json => {
                if(!response.ok) {
                    return Promise.reject(json);
                }
                return json;
            })
        );
};

export function getPizzas(page, size) {
    page = page || 0;
    size = size || PAGE_SIZE;

    return request({
        url: "http://localhost:8080/api/pizza/actives",
        //url: API_BASE_URL + "/pizza/actives?page=" + page + "&size=" + size,
        method: 'GET'
    });
}
const ingredients = [{
    name:"poivre",
    ingredient_id:0
}, {
    name: "sel",
    ingredient_id:1
}, {name:"tomate",
    ingredient_id:2
}];

const allPizzas = [//TODO TO REMOVE
    {id: 0, title: "pizza 1", ingredients: [ingredients[0], ingredients[1]]},
    {id: 1, title: "pizza 2", ingredients: [ingredients[0], ingredients[2]]},
    {id: 2, title: "pizza 3", ingredients: [ ingredients[1], ingredients[3]]},
    {id: 3, title: "pizza 4", ingredients: ingredients},
];

export function getPizzasFake(page, size) {
    page = page || 0;
    size = size || PAGE_SIZE;

    let sliced = [];
    const startIndex = page * size;
    for (let i = startIndex; i < startIndex + size; i++) {
        sliced.push(allPizzas[i])
    }
    return sliced;
}

export function getPizzaCount() {
    return allPizzas.length;
}

export function getAllIngredients() {
    return ingredients;
}









export function createPoll(pollData) {
    return request({
        url: API_BASE_URL + "/polls",
        method: 'POST',
        body: JSON.stringify(pollData)
    });
}

export function castVote(voteData) {
    return request({
        url: API_BASE_URL + "/polls/" + voteData.pollId + "/votes",
        method: 'POST',
        body: JSON.stringify(voteData)
    });
}

export function login(loginRequest) {
    return request({
        url: API_BASE_URL + "/auth/signin",
        method: 'POST',
        body: JSON.stringify(loginRequest)
    });
}

export function signup(signupRequest) {
    return request({
        url: API_BASE_URL + "/auth/signup",
        method: 'POST',
        body: JSON.stringify(signupRequest)
    });
}

export function checkUsernameAvailability(username) {
    return request({
        url: API_BASE_URL + "/user/checkUsernameAvailability?username=" + username,
        method: 'GET'
    });
}

export function checkEmailAvailability(email) {
    return request({
        url: API_BASE_URL + "/user/checkEmailAvailability?email=" + email,
        method: 'GET'
    });
}


export function getCurrentUser() {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/user/me",
        method: 'GET'
    });
}

export function getUserProfile(username) {
    return request({
        url: API_BASE_URL + "/users/" + username,
        method: 'GET'
    });
}

export function getUserCreatedPolls(username, page, size) {
    page = page || 0;
    size = size || PAGE_SIZE;

    return request({
        url: API_BASE_URL + "/users/" + username + "/polls?page=" + page + "&size=" + size,
        method: 'GET'
    });
}

export function getUserVotedPolls(username, page, size) {
    page = page || 0;
    size = size || PAGE_SIZE;

    return request({
        url: API_BASE_URL + "/users/" + username + "/votes?page=" + page + "&size=" + size,
        method: 'GET'
    });
}