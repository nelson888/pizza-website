import axios from "axios";

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

export default {
    get: axios.get,
    put: axios.put,
    delete: axios.delete
};