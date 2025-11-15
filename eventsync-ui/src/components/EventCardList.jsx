import { useState, useEffect } from "react";
import { MainContainer } from "../styles/StyledComponents";
import { getEvents } from "../api/EventApi";
import EventCard from "./EventCard";
import EventForm from "./EventForm";

function EventCardList() {
  const [events, setEvents] = useState([]);

  const fetchEvents = async () => {
    try {
      const response = await getEvents();
      setEvents(response.data);
    } catch (error) {
      console.error("Error fetching events:", error);
    }
  };

  useEffect(() => {
    fetchEvents();
  }, []);

  return (
    <MainContainer>
      <EventForm refreshEvents={fetchEvents} />
      {events && events.length > 0 && (
        <>
          {events.map((event) => (
            <EventCard
              key={event.id}
              title={event.title}
              description={event.description}
            />
          ))}
        </>
      )}
    </MainContainer>
  );
}
export default EventCardList;
