import http from '../utils/httpService';
import {API_BASE_URL} from "./constants";

export const PAGE_SIZE = 2;

export function getPizzasRequest(page, size) {
    page = page || 0;
    size = size || PAGE_SIZE;

    return http.get(API_BASE_URL + `/pizza?page=${page}&size=${size}`);
}

export function getPizzaCountRequest() {
    return axios.get(API_BASE_URL + '/pizza/count');
}

export function getPizzasSearchRequest(page, searchQuery) {
    http.get(API_BASE_URL + `/pizza?${searchQuery}`);
}

export function postPizzaRequest(pizza) {
    return http.post(API_BASE_URL, pizza); // in the data there should be the newly posted pizza
}

export function updatePizzaRequest(pizza) {
    return http.put(API_BASE_URL + `/${pizza.id}`, pizza); // in the data there should be the newly posted pizza
}

export function deletePizzaRequest(pizza) {
    /*
    try {

    } catch (e) {
        if (e.response && e.response.status === 404) {
            //TODO
        }
    }*/
    return http.delete(API_BASE_URL + `/${pizza.id}`);
}
