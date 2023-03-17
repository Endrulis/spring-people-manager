import { useNavigate } from "react-router-dom";
import PersonService from "../../services/PersonService";
const Person = ({ person, onDelete }) => {
  const { id, name, age, image } = person;
  const navigate = useNavigate();

  const handleDelete = async () => {
    try {
      await PersonService.deleteById(id);
      onDelete(id);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <article key={id} className="person grid grid-cols-3 mb-5 items-center">
      <img
        src={image}
        alt={name}
        className="w-24 h-24 object-cover rounded-full shadow-xl"
      />
      <div className="  ">
        <h4 className="mb-0.5">{name}</h4>
        <p className="mb-0">{age} years</p>
      </div>
      <div>
        <button
          onClick={() => navigate(`/editPerson/${id}`)}
          className="rounded bg-gray-800 hover:bg-purple-700   text-white font-bold"
        >
          Edit
        </button>
        <button
          className="rounded bg-gray-800 hover:bg-purple-700 text-white font-bold"
          onClick={handleDelete}
        >
          Delete
        </button>
      </div>
    </article>
  );
};
export default Person;
