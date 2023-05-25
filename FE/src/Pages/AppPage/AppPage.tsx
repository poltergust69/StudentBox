import React, { useState } from "react";
import "../../index.css";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import NavbarLayout from "../../Components/Navbar/Navbar";
import "bootstrap/dist/css/bootstrap.min.css";
import LoginPage from "../LoginPage/LoginPage";
import LogOutPage from "../LogOutPage/LogOutPage";
import { EmptyProps } from "../../Models/Shared/SharedInterfaces";
import ForumPage from "../ForumPage/ForumPage";
import RegisterPage from "../RegisterPage/RegisterPage";

const AppPage: React.FC<EmptyProps> = (props: EmptyProps) => {
  const [changeCounter, setChangeCounter] = useState<number>(0);

  const updateChangeCounter = (): void => {
    const newChangeCounter = Number.parseInt(changeCounter.toString()) + 1;
    setChangeCounter(newChangeCounter);
  };

  const router = createBrowserRouter([
    {
      path: "/",
      element: (
        <>
          <h1 className="display-1 text-center mt-5 pt-5">
            Welcome to StudentBox
          </h1>
        </>
      ),
    },
    {
      path: "login",
      element: <LoginPage callback={updateChangeCounter} />,
    },
    {
      path: "logout",
      element: <LogOutPage callback={updateChangeCounter} />,
    },
    {
      path: "register",
      element: <RegisterPage callback={updateChangeCounter} />,
    },
    {
      path: "forum",
      element: <ForumPage />,
    },
    {
      path: "forum/posts/:postId",
      element: <></>,
    },
  ]);

  return (
    <>
      <NavbarLayout changeCounter={changeCounter} />
      <RouterProvider router={router} />
    </>
  );
};

export default AppPage;
