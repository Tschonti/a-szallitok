import { Route, Routes } from "react-router-dom";
import { IndexPage } from "./pages/index/IndexPage";
import { MapPage } from "./pages/map/MapPage";

const App = () => {
  return (
    <Routes>
      <Route path="/">
        <Route index element={<IndexPage/>} />
        <Route path="/map" element={<MapPage/>} />
      </Route>

    </Routes>
  );
}

export default App;
