import React from "react";

function PageNotFound() {
  return (
    <div>
      <h1 className="text-4xl text-center mt-20">404 - Page Not Found</h1>
      <p className="text-center mt-4">
        The page you are looking for does not exist.
      </p>
      <p className="text-center mt-2">
        <a href="/" className="text-blue-500 hover:underline hover:scale-105">
          Go back to Home
        </a>
      </p>
    </div>
  );
}

export default PageNotFound;
