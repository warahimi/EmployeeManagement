import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";

function EditEmployee() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [employee, setEmployee] = useState({
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    department: "",
  });

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // ✅ Always fetch employee from backend by ID
  useEffect(() => {
    const fetchEmployeeById = async () => {
      try {
        const res = await fetch(`http://localhost:8080/api/employees/${id}`);
        if (!res.ok) throw new Error("Failed to fetch employee");
        const data = await res.json();
        setEmployee(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchEmployeeById();
  }, [id]);

  const handleUpdate = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(
        `http://localhost:8080/api/employees/${id}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(employee),
        }
      );

      if (!response.ok) throw new Error("Failed to update employee");

      navigate("/"); // go back to dashboard
    } catch (err) {
      setError(err.message);
    }
  };

  if (loading) {
    return (
      <div className="min-h-screen flex items-center justify-center text-gray-500">
        Loading...
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-100 py-10 px-4">
      <div className="max-w-xl mx-auto bg-white p-8 shadow-md rounded-xl">
        <h1 className="text-3xl font-bold text-center text-blue-700 mb-6">
          Edit Employee
        </h1>

        <form onSubmit={handleUpdate} className="space-y-5">
          {["firstName", "lastName", "email", "phone", "department"].map(
            (field) => (
              <div key={field}>
                <label className="block text-sm font-medium capitalize">
                  {field}
                </label>
                <input
                  type="text"
                  value={employee[field]}
                  onChange={(e) =>
                    setEmployee({ ...employee, [field]: e.target.value })
                  }
                  className="w-full p-3 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                  required={field !== "phone"}
                />
              </div>
            )
          )}

          {error && <p className="text-red-600 text-sm">{error}</p>}

          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-3 rounded-lg hover:bg-blue-700 transition"
          >
            Save Changes
          </button>
          <button
            type="button"
            onClick={() => navigate(-1)} // go back
            className="w-full bg-blue-600 text-white py-3 rounded-lg hover:bg-blue-700 transition"
          >
            Cancel
          </button>
        </form>
      </div>
    </div>
  );
}

export default EditEmployee;
