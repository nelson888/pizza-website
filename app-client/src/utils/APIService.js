import axios from 'axios';
import {toast} from 'react-toastify';

axios.interceptors.response.use(success => {

    },
    error => {
        const {response} = error;
        if (response && response.status < 400 || response.status >= 500) {
            console.log(error);
            toast.error('An unexpected error occurred');
        }

        return Promise.reject(error); //pass control to catch block
    });

export const API_BASE_URL = 'http://localhost:8080/api';
//export const API_BASE_URL = '/api';
export const ACCESS_TOKEN = 'accessToken';

export const PAGE_SIZE = 2;



export function getAllIngredientsRequest() {
    return axios.get(API_BASE_URL + '/ingredients');
}

export function getPizzasRequest(page, size) {
    page = page || 0;
    size = size || PAGE_SIZE;

    return axios.get(API_BASE_URL + `/pizza?page=${page}&size=${size}`);
}

export function getPizzaCountRequest() {
    return axios.get(API_BASE_URL + '/pizza/count');
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


export default {
    get: axios.get,
    put: axios.put,
    delete: axios.delete
};