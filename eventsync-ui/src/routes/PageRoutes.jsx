import { Navigate, Route, Routes } from "react-router-dom";
import EventCardList from "../components/EventCardList";

function PageRoutes() {
  return (
    <Routes>
      <Route path="/index.html" element={<Navigate replace to="/" />} />
      <Route path="/" element={<EventCardList />} />
    </Routes>
  );
}

export default PageRoutes;
