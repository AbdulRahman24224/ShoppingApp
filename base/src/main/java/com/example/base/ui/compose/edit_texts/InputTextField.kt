package com.example.base.ui.compose.edit_texts

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.base.ui.compose.sV
import com.example.base.ui.compose.text.bodyStyle
import com.example.base.ui.compose.text.boldStyle
import com.example.base.ui.compose.text.error500TextStyle
import com.example.base.ui.compose.text.hintTextStyle
import com.example.base.ui.theme.AppColors
import com.example.base.ui.theme.SharedFontSize

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    contentTextStyle: TextStyle,
    hintStyle: TextStyle = hintTextStyle(),
    hint: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = false,
    showBottomLine: Boolean = true,
    startText: String = "",
    maxLines: Int = Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    cursorColor: Color = AppColors.Green1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    contentAlignment: Alignment = Alignment.CenterStart,
    showError: Boolean = false,
    errorMessage: String = "",
) {

    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        visualTransformation = visualTransformation,
        textStyle = contentTextStyle,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset {
                        if (contentTextStyle.textAlign == TextAlign.Start)
                            IntOffset(x = 10, y = 0)
                        else
                            IntOffset(x = 0, y = 0)
                    },
                contentAlignment = contentAlignment,
            ) {

                Column {

                    Row {
                        if (startText.isNotEmpty()) {
                            Text(
                                text = startText,
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = SharedFontSize.Small2,
                                    fontWeight = W500,
                                    fontFamily = LocalTextStyle.current.fontFamily
                                ),
                                modifier = Modifier.padding(end = 5.dp)
                            )
                        }
                        Box(Modifier.weight(1f)) {
                            if (value.isEmpty()) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = hint,
                                    style = hintStyle,
                                    color = hintStyle.color,
                                    fontSize = hintStyle.fontSize,
                                    maxLines = if (singleLine) 1 else Int.MAX_VALUE
                                )
                            }
                            innerTextField()
                        }
                    }

                    if (showBottomLine) {
                        Divider(
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .height(1.dp)
                                .background(AppColors.Main)
                        )
                    }


                    if (showError) {
                        sV(8)
                        Text(
                            text = errorMessage,
                            style = error500TextStyle()
                        )
                    }

                }
            }
        },
        enabled = enabled,
        readOnly = readOnly,
        singleLine = singleLine,
        maxLines = maxLines,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        cursorBrush = SolidColor(cursorColor)
    )
}

@Composable
fun EditTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String? = null,
    label: String,
    contentTextStyle: TextStyle = boldStyle().copy(
        color = AppColors.Text,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Normal
    ),
    hintStyle: TextStyle = boldStyle().copy(
        color = AppColors.Grey400,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Normal
    ),
    prefixText: String? = null,
    trailingText: String? = null,
    errorMessage: String = "",
    showError: Boolean = false,
    enabled: Boolean = true,
    onTextChange: (String) -> Unit,
    onDoneClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    onEndDrawableIconClick: () -> Unit = {},
    onStartDrawableIconClick: () -> Unit = {},
    onFocusChanged: (Boolean) -> Unit = {},
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    @DrawableRes endDrawableIcon: Int? = null,
    @DrawableRes startDrawableIcon: Int? = null,
    maxLines: Int = 1,
    isFocused: Boolean = false
) {
    var focusState by remember { mutableStateOf(isFocused) }
    val backgroundColor by animateColorAsState(targetValue = if (focusState.or(showError)) AppColors.White else AppColors.EditTextCardColor)

    val borderColor by animateColorAsState(
        targetValue = if (showError) {
            AppColors.Error
        } else {
            if (focusState) AppColors.Primary600 else Color.Transparent
        }
    )

    Column(modifier = modifier) {

        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = label,
                style = bodyStyle()
            )
            if (trailingText.isNullOrEmpty()) {
                Spacer(modifier = Modifier.weight(1f))
            } else {
                Text(
                    text = trailingText,
                    style = bodyStyle().copy(color = AppColors.Grey8)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            modifier = Modifier

                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(5.dp)
                )
                .border(
                    border = BorderStroke(1.dp, color = borderColor),
                    shape = RoundedCornerShape(5.dp)
                ),
        ) {

            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                prefixText?.let {
                    Text(
                        text = it,
                        style = boldStyle().copy(
                            color = AppColors.Text,
                            fontWeight = FontWeight.Normal
                        ),
                    )
                }

                InputTextField(
                    modifier = Modifier
                        .sizeIn(minHeight = 40.dp)
                        .padding(
                            start = (if (startDrawableIcon == null) 0 else 30).dp,
                            end = 40.dp
                        )
                        .onFocusChanged {
                            focusState = it.isFocused
                            onFocusChanged.invoke(it.isFocused)
                        },
                    value = value,
                    enabled = enabled,
                    showBottomLine = false,
                    onValueChange = onTextChange,
                    contentTextStyle = contentTextStyle,
                    maxLines = maxLines,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        imeAction = imeAction,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { onDoneClick() },
                        onNext = { onNextClick() }
                    ),
                    hint = hint ?: "",
                    hintStyle = hintStyle,
                    visualTransformation = visualTransformation,
                )
            }

            if (endDrawableIcon != null) {
                IconButton(
                    onClick = onEndDrawableIconClick,
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 20.dp)
                        .size(24.dp)
                        .clip(CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = endDrawableIcon),
                        contentDescription = "scan card icon",
                        tint = AppColors.Primary600
                    )
                }
            }

            if (startDrawableIcon != null) {
                IconButton(
                    onClick = onStartDrawableIconClick,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 8.dp)
                        .size(30.dp)
                        .clip(CircleShape)
                ) {
                    Icon(
                        painter = painterResource(id = startDrawableIcon),
                        contentDescription = "start drawable",
                        tint = AppColors.Primary600
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        AnimatedVisibility(visible = showError && errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                style = error500TextStyle().copy(
                    textAlign = TextAlign.Start,
                    fontSize = SharedFontSize.Piddling2
                )
            )
        }
    }
}