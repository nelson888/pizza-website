import axios from 'axios';

axios.interceptors.response.use(success => {

    },
    error => {
    const {response} = error;
    if (response && response.status < 400 || response.status >= 500) {
        console.log(error);
        alert('An unexpected error occured');
    }

    return Promise.reject(error); //pass control to catch block
    });

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

export function getPizzasRequest(page, size) {
    page = page || 0;
    size = size || PAGE_SIZE;

    return axios.get(API_BASE_URL + `/pizza?page=${page}&size=${size}`);
}

export function getPizzaCountRequest() {
    return axios.get(API_BASE_URL + '/pizza/count');
}

export function getAllIngredients() {
    return ingredients;
}

export function getPizzasSearchRequest(page, searchQuery) {
    axios.get(API_BASE_URL + `/pizza?${searchQuery}`);
}

export function postPizzaRequest(pizza) {
    return axios.post(API_BASE_URL, pizza); // in the data there should be the newly posted pizza
}

export function updatePizzaRequest(pizza) {
    return axios.put(API_BASE_URL + `/${pizza.id}`, pizza); // in the data there should be the newly posted pizza
}

export function deletePizzaRequest(pizza) {
    /*
    try {

    } catch (e) {
        if (e.response && e.response.status === 404) {
            //TODO
        }
    }*/
    return axios.delete(API_BASE_URL + `/${pizza.id}`);
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