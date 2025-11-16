import { Form, Field, Formik } from "formik";
import { FormActionButtonBox, FormBox } from "../styles/StyledComponents";
import { Button, TextField } from "@mui/material";
import { addEvent } from "../api/EventApi";
import { eventFormSchema } from "../schemas";

function EventForm({ refreshEvents }) {
  const onSubmit = async (values, { setSubmitting, resetForm }) => {
    try {
      await addEvent(values);
      refreshEvents();
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
        title: "",
        description: "",
      }}
      validationSchema={eventFormSchema}
      onSubmit={onSubmit}
    >
      {({ isSubmitting, errors, touched }) => (
        <Form>
          <FormBox>
            <Field
              label="Title"
              as={TextField}
              placeholder="Title"
              name="title"
              error={touched.title && !!errors.title}
              helperText={touched.title && errors.title}
              type="text"
              fullWidth
              multiline
              style={{ marginBottom: "1rem" }}
            />

            <Field
              label="Description"
              as={TextField}
              name="description"
              type="text"
              error={touched.description && !!errors.description}
              helperText={touched.description && errors.description}
              fullWidth
              multiline
              minRows={4}
              style={{ marginBottom: "0.5rem" }}
            />
            <FormActionButtonBox>
              <Button type="submit" variant="outlined" disabled={isSubmitting}>
                Add
              </Button>
            </FormActionButtonBox>
          </FormBox>
        </Form>
      )}
    </Formik>
  );
}
export default EventForm;
