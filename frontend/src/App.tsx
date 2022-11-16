import { Route, Routes } from "react-router-dom";
import { DeliveryPage } from "./pages/deliveries/DeliveryPage";
import { IndexPage } from "./pages/index/IndexPage";
import { MapPage } from "./pages/map/MapPage";
import { ToplistPage } from "./pages/toplist/ToplistPage";
import { UserPage } from "./pages/users/UserPage";

const App = () => {
  return (
    <Routes>
      <Route path="/">
        <Route index element={<IndexPage/>} />
        <Route path="/map" element={<MapPage/>} />
        <Route path="/deliveries" element={<DeliveryPage/>} />
        <Route path="/users" element={<UserPage/>} />
        <Route path="/toplist" element={<ToplistPage/>} />
      </Route>

    </Routes>
  );
}

export default App;
