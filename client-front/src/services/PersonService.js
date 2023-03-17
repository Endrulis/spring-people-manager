import axios from "axios";

const peopleAPI = axios.create({
  baseURL: "http://localhost:8080/api/v1/people",
});

const PersonService = {
  savePerson: (person) => peopleAPI.post("", person),
  getPeople: () => peopleAPI.get(""),
  getPersonById: (id) => peopleAPI.get(`/${id}`),
  updatePerson: (person, id) => peopleAPI.put(`/${id}`, person),
  deleteAllPeople: () => peopleAPI.delete(""),
  deleteById: (id) => peopleAPI.delete(`${id}`),
};
export default PersonService;
