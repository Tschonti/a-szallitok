import { useContext } from "react";
import { useNavigate } from "react-router-dom";
import { AuthContext } from "../../auth/AuthContext";
import { Page } from "../../components/Page";
import { Forbidden } from "./components/Forbidden";
import { LoginButton } from "./components/LoginButton";

export const IndexPage = () => {
  const { user } = useContext(AuthContext)
  const navigate = useNavigate()

  if (user && user.isAdmin) {
    navigate('/map')
  }

  return (
    <Page>
        {user ?
        <Forbidden />
        : <LoginButton />}
    </Page>
  );
}