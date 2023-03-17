import React, { useEffect, useCallback } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { useFormik } from "formik";
import * as Yup from "yup";
import PersonService from "../services/PersonService";

const validationSchema = Yup.object({
  name: Yup.string().required("Full name is required"),
  age: Yup.string().required("Age is required"),
  image: Yup.string()
    .url("Invalid image URL")
    .required("Image URL is required"),
});

const UpdatePerson = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const formik = useFormik({
    initialValues: {
      name: "",
      age: "",
      image: "",
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      const updatedPerson = { ...values, id };

      PersonService.updatePerson(updatedPerson, id)
        .then((response) => {
          navigate("/");
        })
        .catch((error) => {
          console.log(error);
        });
    },
  });
  const { handleSubmit, values, errors, touched, handleChange } = formik;

  const reset = (e) => {
    e.preventDefault();
    formik.resetForm();
  };
  const fetchData = useCallback(async () => {
    try {
      const response = await PersonService.getPersonById(id);
      formik.setValues({
        name: response.data.name,
        age: response.data.age,
        image: response.data.image,
      });
    } catch (error) {
      console.log(error);
    }
    // eslint-disable-next-line
  }, [id]);

  useEffect(() => {
    fetchData();
  }, [fetchData]);

  return (
    <div className="flex justify-center">
      <div className="w-full max-w-lg bg-gray-100 px-6 py-8 my-24 rounded shadow-md">
        <h1 className="text-3xl font-bold mb-8 text-center">Add New Person</h1>
        <form onSubmit={handleSubmit} className="space-y-6">
          <div className="mb-4">
            <label htmlFor="name" className="block text-gray-700 font-bold">
              Full Name
            </label>
            <input
              id="name"
              name="name"
              type="text"
              value={values.name}
              onChange={handleChange}
              className="w-full px-4 py-2 mt-2 text-gray-700 bg-gray-200 rounded-md border-2 border-gray-200 focus:outline-none focus:bg-white focus:border-purple-500"
            />
            {errors.name && touched.name && (
              <div className="text-red-500 mt-2">{errors.name}</div>
            )}
          </div>
          <div className="mb-4">
            <label htmlFor="age" className="block text-gray-700 font-bold">
              Age
            </label>
            <input
              id="age"
              name="age"
              type="number"
              value={values.age}
              onChange={handleChange}
              className="w-full px-4 py-2 mt-2 text-gray-700 bg-gray-200 rounded-md border-2 border-gray-200 focus:outline-none focus:bg-white focus:border-purple-500"
            />
            {errors.age && touched.age && (
              <div className="text-red-500 mt-2">{errors.age}</div>
            )}
          </div>
          <div className="mb-4">
            <label htmlFor="image" className="block text-gray-700 font-bold">
              Image URL
            </label>
            <input
              id="image"
              name="image"
              type="url"
              value={values.image}
              onChange={handleChange}
              className="w-full px-4 py-2 mt-2 text-gray-700 bg-gray-200 rounded-md border-2 border-gray-200 focus:outline-none focus:bg-white focus:border-purple-500"
            />
            {errors.image && touched.image && (
              <div className="text-red-500 mt-2">{errors.image}</div>
            )}
          </div>
          <div className="flex justify-between">
            <button
              type="submit"
              className="bg-purple-700 text-white font-bold py-2 px-4 rounded-md hover:bg-purple-600 focus:outline-none focus:bg-purple-600 mr-4"
            >
              Save
            </button>
            <button
              type="button"
              onClick={reset}
              className="bg-gray-800 text-white font-bold py-2 px-4 rounded-md hover:bg-purple-700 focus:outline-none focus:bg-purple-700 mr-4"
            >
              Clear
            </button>
            <button
              type="button"
              onClick={() => navigate("/")}
              className="bg-gray-800 text-white font-bold py-2 px-4 rounded-md hover:bg-purple-700 focus:outline-none focus:bg-purple-700"
            >
              Cancel
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};
export default UpdatePerson;
