import axios from "axios"

//상품관련 공통 URL
const URL = "http://localhost:7777/products";

//상품목록
export const getProducts = ()=>axios.get(URL);

//상품등록
export const registProduct = (data)=>axios.post(URL, data);

//상품 한건
export const getProduct = (productId)=>axios.get(`${URL}/${productId}`);

//상품 수정
export const updateProduct = (productId, data)=>axios.put(`${URL}/${productId}`, data);

//상품 삭제
export const deleteProduct = (productId)=>axios.delete(`${URL}/${productId}`);