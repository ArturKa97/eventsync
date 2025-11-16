import { useState, useEffect } from "react";
import {
  MainContainer,
  MainContainerColumn,
  NoEventsTypography,
} from "../styles/StyledComponents";
import { deleteEventById, getEvents } from "../api/EventApi";
import EventCard from "./EventCard";
import EventForm from "../forms/EventForm";
import { useNavigate } from "react-router-dom";
import { Typography } from "@mui/material";

function EventCardList() {
  const [events, setEvents] = useState([]);
  const navigate = useNavigate();

  const selectEvent = (id) => {
    navigate("/info", { state: id });
  };

  const fetchEvents = async () => {
    try {
      const response = await getEvents();
      setEvents(response.data);
    } catch (error) {
      console.error("Error fetching events:", error);
      setEvents([]);
    }
  };
  const deleteEvent = async (eventId) => {
    try {
      await deleteEventById(eventId);
      fetchEvents();
    } catch (error) {
      console.error("Error fetching events:", error);
    }
  };

  useEffect(() => {
    fetchEvents();
  }, []);

  return (
    <MainContainerColumn>
      <EventForm refreshEvents={fetchEvents} />
      {events && events.length > 0 ? (
        <MainContainer>
          {events.map((event) => (
            <EventCard
              key={event.id}
              onClick={() => selectEvent(event.id)}
              title={event.title}
              description={event.description}
              onDelete={() => deleteEvent(event.id)}
            />
          ))}
        </MainContainer>
      ) : (
        <NoEventsTypography>No events available</NoEventsTypography>
      )}
    </MainContainerColumn>
  );
}
export default EventCardList;
