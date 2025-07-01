import React from "react";
import { Link } from "react-router-dom";

function Header() {
  return (
    <div className="bg-gray-800 text-white p-4 flex justify-between items-center text-lg pl-5 pr-15 ">
      <h1 className="text-2xl font-semibold">Employee Management System</h1>
      <div className="flex space-x-4">
        <Link to="/" className="hover:scale-106">
          Home
        </Link>
        <Link className="hover:scale-106">Manage Employee</Link>
        <Link to="/add-employee" className="hover:scale-106">
          Add Employee
        </Link>
      </div>
    </div>
  );
}

export default Header;
