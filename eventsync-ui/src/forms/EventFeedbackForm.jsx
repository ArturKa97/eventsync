import { Form, Field, Formik } from "formik";
import {
  FeedbackFormBox,
  FormActionButtonBox,
  FormTextFieldBox,
} from "../styles/StyledComponents";
import { Button, TextField } from "@mui/material";
import { addEventFeedback } from "../api/EventApi";
import { eventFeedbackFormSchema } from "../schemas";

function EventFeedbackForm({ eventId, refreshEvent, refreshSummary }) {
  const onSubmit = async (values, { setSubmitting, resetForm }) => {
    try {
      await addEventFeedback(eventId, values);
      refreshEvent();
      refreshSummary();
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
      validationSchema={eventFeedbackFormSchema}
      onSubmit={onSubmit}
    >
      {({ isSubmitting, errors, touched, values }) => (
        <Form>
          <FeedbackFormBox>
            <FormTextFieldBox>
              <Field
                label="Feedback"
                as={TextField}
                placeholder="Write your feedback here..."
                name="feedback"
                type="text"
                error={touched.feedback && !!errors.feedback}
                helperText={touched.feedback && errors.feedback}
                multiline
                rows={4}
                fullWidth
              />
            </FormTextFieldBox>
            <FormActionButtonBox>
              <Button
                type="submit"
                variant="outlined"
                disabled={isSubmitting || !values.feedback.trim()}
              >
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
