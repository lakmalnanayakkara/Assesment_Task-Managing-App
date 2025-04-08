import {
  Component,
  EventEmitter,
  Input,
  Output,
  SimpleChanges,
} from '@angular/core';

@Component({
  selector: 'app-message-box',
  standalone: false,
  templateUrl: './message-box.component.html',
  styleUrl: './message-box.component.css',
})
export class MessageBoxComponent {
  @Input() message: string;
  @Input() isError: boolean;
  @Output() isErrorEventEmit = new EventEmitter<boolean>();

  ngOnChanges(changes: SimpleChanges) {
    if (changes['message'] && this.message) {
      setTimeout(() => {
        this.closeAlert();
      }, 5000);
    }
  }

  closeAlert() {
    this.isErrorEventEmit.emit(false);
  }
}
