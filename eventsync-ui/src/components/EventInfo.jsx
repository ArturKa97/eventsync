import { Typography } from "@mui/material";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { getEventById } from "../api/EventApi";
import EventFeedbackForm from "../forms/EventFeedbackForm";

function EventInfo() {
  const [eventInfo, setEventInfo] = useState({});
  const location = useLocation(); //getting the id passed down from clicking the EventCard

  const fetchEvent = async () => {
    try {
      const response = await getEventById(location.state);
      setEventInfo(response.data);
    } catch (error) {
      console.error("Error fetching event:", error);
    }
  };

  useEffect(() => {
    fetchEvent();
  }, []);

  return (
    <>
      <Typography>{eventInfo.title}</Typography>
      <Typography>{eventInfo.description}</Typography>
      <EventFeedbackForm eventId={eventInfo.id} />
      {eventInfo.eventFeedbackList?.map((eventFeedback, index) => (
        <div key={index}>
          <Typography>{eventFeedback.timeStamp}</Typography>
          <Typography>{eventFeedback.feedback}</Typography>
          <Typography>{eventFeedback.eventSentimentDTO.label}</Typography>
          <Typography>{eventFeedback.eventSentimentDTO.score}</Typography>
        </div>
      ))}
    </>
  );
}
export default EventInfo;
