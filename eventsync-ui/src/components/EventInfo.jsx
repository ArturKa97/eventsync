import { Typography } from "@mui/material";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import { getEventById } from "../api/EventApi";

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
      {eventInfo.eventFeedbackList?.map((eventFeedback, index) => (
        <div key={index}>
          <Typography>{eventFeedback.timeStamp}</Typography>
          <Typography>{eventFeedback.feedback}</Typography>
        </div>
      ))}
    </>
  );
}
export default EventInfo;
