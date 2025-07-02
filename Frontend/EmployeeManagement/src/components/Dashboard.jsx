import React, { useState, useEffect } from "react";
import EmployeeCard from "./EmployeeCard"; // import the new component
import { useNavigate } from "react-router-dom";

function Dashboard() {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [searchTerm, setSearchTerm] = useState("");
  const [sortOrder, setSortOrder] = useState("asc");
  const navigate = useNavigate();

  useEffect(() => {
    fetchEmployees();
  }, []);

  const fetchEmployees = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/employees");
      if (!response.ok) throw new Error("Failed to fetch employee data");
      const data = await response.json();
      setEmployees(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id) => {
    // Confirm before deleting
    if (!window.confirm("Are you sure you want to delete this employee?")) {
      return;
    }
    try {
      await fetch(`http://localhost:8080/api/employees/${id}`, {
        method: "DELETE",
      });
      setEmployees(employees.filter((e) => e.id !== id));
    } catch (err) {
      alert("Failed to delete employee.");
    }
  };

  const handleEdit = (employee) => {
    if (!window.confirm("Do you want to edit this employee?")) {
      return;
    }
    navigate(`/edit/${employee.id}`, { state: { employee } });
  };

  const filteredEmployees = employees
    .filter((employee) =>
      `${employee.firstName} ${employee.lastName}`
        .toLowerCase()
        .includes(searchTerm.toLowerCase())
    )
    .sort((a, b) =>
      sortOrder === "asc"
        ? a.firstName.localeCompare(b.firstName)
        : b.firstName.localeCompare(a.firstName)
    );

  return (
    <div className="min-h-screen bg-gray-100 py-10 px-4">
      <h1 className="text-4xl font-bold text-center text-blue-700 mb-8">
        Employee Dashboard
      </h1>

      <div className="max-w-3xl mx-auto bg-white p-6 shadow-lg rounded-xl text-lg">
        <div className="mb-4">
          <input
            type="text"
            placeholder="Search employees by name..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="w-full p-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400"
          />
        </div>

        <div className="mb-6">
          <select
            value={sortOrder}
            onChange={(e) => setSortOrder(e.target.value)}
            className="w-full p-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-400"
          >
            <option value="asc">Sort by First Name (A-Z)</option>
            <option value="desc">Sort by First Name (Z-A)</option>
          </select>
        </div>

        {loading ? (
          <p className="text-center text-gray-500">Loading employees...</p>
        ) : error ? (
          <p className="text-center text-red-600 font-semibold">{error}</p>
        ) : filteredEmployees.length === 0 ? (
          <p className="text-center text-gray-600">
            No employees match your search.
          </p>
        ) : (
          <ul className="space-y-4">
            {filteredEmployees.map((employee) => (
              <EmployeeCard
                key={employee.id}
                employee={employee}
                onDelete={handleDelete}
                onEdit={handleEdit}
              />
            ))}
          </ul>
        )}
      </div>
    </div>
  );
}

export default Dashboard;
