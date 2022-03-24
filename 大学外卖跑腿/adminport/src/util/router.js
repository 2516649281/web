import axios from "axios";

const request = axios.create({
  baseURL: "http://192.168.1.127:8080",
});

export default request;
