import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Person from "./entities/Person";
import PersonService from "../services/PersonService";

const PersonList = () => {
  const [people, setPeople] = useState(null);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await PersonService.getPeople();
        console.log(response.data);
        setPeople(response.data);
        setLoading(false);
      } catch (error) {
        console.log(error);
      }
    };
    fetchData();
  }, []);

  const deletePerson = (id) => {
    const updatedPeople = people.filter((person) => person.id !== id);
    setPeople(updatedPeople);
  };

  return (
    <div className="flex justify-center max-w-screen-sm mx-auto">
      <section className="w-full max-w-3xl my-24 p-8 bg-white rounded shadow">
        {loading ? (
          <h2>Loading...</h2>
        ) : people ? (
          <>
            <button
              onClick={() => navigate("/addPerson")}
              className="bg-gray-800 hover:bg-purple-700 text-white font-bold py-2 px-4 my-4 w-full rounded-md"
            >
              Add person
            </button>
            <h3 className="font-normal mb-4">{people.length} people today</h3>
            {people.map((person) => (
              <Person key={person.id} person={person} onDelete={deletePerson} />
            ))}
            <button
              onClick={() => {
                PersonService.deleteAllPeople();
                setPeople([]);
              }}
              className="bg-gray-800 hover:bg-purple-700 text-white font-bold py-2 px-4 my-4 w-full rounded-md"
            >
              Clear all
            </button>
          </>
        ) : (
          <p>No people today</p>
        )}
      </section>
    </div>
  );
};

export default PersonList;
