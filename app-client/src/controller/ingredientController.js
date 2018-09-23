import http from '../utils/httpService';
import {API_BASE_URL} from "./constants";

export function getAllIngredientsRequest() {
    return http.get(API_BASE_URL + '/ingredients');
}