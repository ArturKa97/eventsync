import {
  StyledCard,
  StyledCardActionArea,
  StyledCardContent,
  StyledDescriptionTypography,
  StyledTitleTypography,
} from "../styles/StyledComponents";
import { Button } from "@mui/material";

function EventCard({ title, description, onClick, onDelete }) {
  return (
    <StyledCard>
      <StyledCardActionArea onClick={onClick}>
        <StyledCardContent>
          <StyledTitleTypography variant="h5">{title}</StyledTitleTypography>
          <StyledDescriptionTypography variant="body2">
            {description}
          </StyledDescriptionTypography>
        </StyledCardContent>
      </StyledCardActionArea>
      <Button
        variant="contained"
        color="error"
        onClick={(e) => {
          e.stopPropagation();
          onDelete();
        }}
        style={{ width: "100%", borderRadius: 0 }}
      >
        Delete
      </Button>
    </StyledCard>
  );
}
export default EventCard;
