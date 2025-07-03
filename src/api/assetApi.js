import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/assets';

export const getAssets = () => axios.get(BASE_URL);
export const addAsset = (asset) => axios.post(BASE_URL, asset);
export const updateAsset = (id, asset) => axios.put(`${BASE_URL}/${id}`, asset);
export const deleteAsset = (id) => axios.delete(`${BASE_URL}/${id}`);