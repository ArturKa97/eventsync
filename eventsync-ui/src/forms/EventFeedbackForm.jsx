import { Form, Field, Formik } from "formik";
import {
  FeedbackFormBox,
  FormActionButtonBox,
  FormTextFieldBox,
} from "../styles/StyledComponents";
import { Button, TextField } from "@mui/material";
import { addEventFeedback } from "../api/EventApi";

function EventFeedbackForm({ eventId }) {
  const onSubmit = async (values, { setSubmitting, resetForm }) => {
    try {
      await addEventFeedback(eventId, values);
      resetForm();
    } catch (error) {
      console.error("Error adding event:", error);
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <Formik
      initialValues={{
        feedback: "",
      }}
      onSubmit={onSubmit}
    >
      {({ isSubmitting, errors, touched }) => (
        <Form>
          <FeedbackFormBox>
            <FormTextFieldBox>
              <Field
                label="Feedback"
                as={TextField}
                placeholder="Write your feedback here..."
                name="feedback"
                multiline
                rows={4}
                fullWidth
              />
            </FormTextFieldBox>
            <FormActionButtonBox>
              <Button type="submit" variant="outlined" disabled={isSubmitting}>
                Add
              </Button>
            </FormActionButtonBox>
          </FeedbackFormBox>
        </Form>
      )}
    </Formik>
  );
}
export default EventFeedbackForm;
