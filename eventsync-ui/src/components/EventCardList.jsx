import { useState, useEffect } from "react";
import { MainContainer } from "../styles/StyledComponents";
import { getEvents } from "../api/EventApi";
import EventCard from "./EventCard";

function EventCardList() {
  const [events, setEvents] = useState([]);

  useEffect(() => {
    getEvents()
      .then((response) => {
        setEvents(response.data);
      })
      .catch((error) => {
        console.log("Error fetching events:", error);
      });
  }, []);

  return (
    <MainContainer>
      {events.map((event) => (
        <EventCard
          key={event.id}
          title={event.title}
          description={event.description}
        />
      ))}
      <EventCard
        title={"TESTAAAAAAAAAAAAAAAAAAAAAAAAAAAAS"}
        description={"description1"}
      />
      <EventCard title={"test2"} description={"description2"} />
      <EventCard title={"test1"} description={"description1"} />
      <EventCard title={"test2"} description={"description2"} />
      <EventCard title={"test1"} description={"description1"} />
      <EventCard title={"test2"} description={"description2"} />
    </MainContainer>
  );
}
export default EventCardList;
