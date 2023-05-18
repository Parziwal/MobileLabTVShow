package hu.bme.aut.tvshowapp.ui.screen.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import hu.bme.aut.tvshowapp.model.Review
import java.util.Locale

@Composable
fun AddReviewDialog(
    addReview: (Review) -> Unit,
    dialogDismiss: () -> Unit
) {
    Dialog(onDismissRequest = dialogDismiss) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(size = 6.dp)
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                var comment by remember { mutableStateOf("") }
                var rating by remember { mutableStateOf(0f) }
                Text(
                    text = "Add Review",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = String.format(Locale.US,"%.1f", rating),
                    style = MaterialTheme.typography.labelLarge
                )
                Slider(
                    value = rating,
                    onValueChange = { rating = it },
                    valueRange = 1f..10f,
                    steps = 17
                )
                OutlinedTextField(
                    value = comment,
                    label = { Text(text = "Comment") },
                    onValueChange = {
                        comment = it
                    },
                    minLines = 3,
                    modifier = Modifier.fillMaxWidth()
                )
                Button(onClick = {
                    addReview(
                        Review(
                        rating = String.format(Locale.US, "%.1f", rating).toDouble(),
                        comment = comment)
                    )
                    dialogDismiss()
                },
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth(0.8f)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Save")
                }
            }
        }
    }
}