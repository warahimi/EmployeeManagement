import React from "react";

function EmployeeCard({ employee, onDelete, onEdit }) {
  return (
    <li className="bg-gray-50 border border-gray-200 rounded-lg p-4 shadow-sm hover:shadow-md transition">
      <div className="pl-2">
        <h2 className="text-lg font-semibold text-gray-800">
          {employee.firstName} {employee.lastName}
        </h2>
        <p className="text-lg font-bold text-gray-600">📧 {employee.email}</p>
        <p className="text-lg  text-gray-600">📞 {employee.phone}</p>
        <p className="text-lg  text-gray-600">🏢 {employee.department}</p>
      </div>
      <div className="mt-4 flex gap-4 ">
        <button
          onClick={() => onEdit(employee)}
          className="px-4 py-2 bg-yellow-400 text-white rounded hover:bg-yellow-500 min-w-[150px] text-center"
        >
          Edit
        </button>
        <button
          onClick={() => onDelete(employee.id)}
          className="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600 min-w-[150px] text-center"
        >
          Delete
        </button>
      </div>
    </li>
  );
}

export default EmployeeCard;
