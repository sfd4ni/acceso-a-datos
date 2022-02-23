import { useNavigate } from "react-router";
import { Login } from "./login";

export const Logout = () => {
  localStorage.clear();
  console.log(localStorage.getItem("token"));
  const navigate  = useNavigate();
  navigate("/login", { replace: true });
  return (<Login/>);
}