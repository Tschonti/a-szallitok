import { Center } from "@chakra-ui/react";
import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../../auth/AuthContext";
import { Forbidden } from "./components/Forbidden";
import { LoginButton } from "./components/LoginButton";

export const IndexPage = () => {
  const { user } = useContext(AuthContext)
  const navigate = useNavigate()

  if (user && user.isAdmin) {
    navigate('/map')
  }

  return (
    <Center>
      {user ?
      <Forbidden />
      : <LoginButton />}
    </Center>
  );
}