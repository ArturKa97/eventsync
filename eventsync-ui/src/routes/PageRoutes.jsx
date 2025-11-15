import { Navigate, Route, Routes } from "react-router-dom";
import EventCardList from "../components/EventCardList";
import EventInfo from "../components/EventInfo";

function PageRoutes() {
  return (
    <Routes>
      <Route path="/index.html" element={<Navigate replace to="/" />} />
      <Route path="/" element={<EventCardList />} />
      <Route path="/info" element={<EventInfo />} />
    </Routes>
  );
}

export default PageRoutes;
