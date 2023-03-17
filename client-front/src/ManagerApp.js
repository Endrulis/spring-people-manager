import { Routes, Route } from "react-router-dom";
import PersonList from "./components/PersonList";
import AddPerson from "./components/AddPerson";
import UpdatePerson from "./components/UpdatePerson";
const PeopleApp = () => {
  return (
    <Routes>
      <Route path="/" element={<PersonList />} />
      <Route path="/addPerson" element={<AddPerson />} />
      <Route path="/editPerson/:id" element={<UpdatePerson />} />
    </Routes>
  );
};

export default PeopleApp;
